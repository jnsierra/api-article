package co.com.ud.business.service.impl;

import co.com.ud.business.rest.client.ArticuloCliente;
import co.com.ud.business.service.DocumentManipulationService;
import co.com.ud.business.service.IdeaService;
import co.com.ud.business.service.ParrafoService;
import co.com.ud.business.service.UsuarioService;
import co.com.ud.utiles.dto.ArticuloDto;
import co.com.ud.utiles.dto.IdeaDto;
import co.com.ud.utiles.dto.ParrafoDto;
import co.com.ud.utiles.dto.UsuarioDto;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DocumentManipulationServiceImpl implements DocumentManipulationService {

    private final String urlFormato;
    private final String urlDestino;
    private final String templateName;
    private final ArticuloCliente articuloCliente;
    private final IdeaService ideaService;
    private final UsuarioService usuarioService;
    private final ParrafoService parrafoService;


    @Autowired
    public DocumentManipulationServiceImpl(@Value("${repositorio.formatoArt.ubicacion}")String urlFormatoArt,
                                           @Value("${repositorio.formatoArt.docDestino}")String urlDestino,
                                           IdeaService ideaService,
                                           UsuarioService usuarioService,
                                           ParrafoService parrafoService,
                                           ArticuloCliente articuloCliente) {
        this.urlFormato = urlFormatoArt;
        this.templateName = "formato_002.docx";
        this.urlDestino = urlDestino;
        this.articuloCliente = articuloCliente;
        this.ideaService = ideaService;
        this.usuarioService = usuarioService;
        this.parrafoService = parrafoService;
    }

    @Override
    public Optional<String> generateFormatArt(String token,Long idArt) throws IOException {
        Optional<ArticuloDto> data = getDataArticulo(token , idArt);
        if(data.isEmpty()){
            return Optional.empty();
        }
        return writeDocument(idArt, data.get(), token);
    }

    @Override
    public Optional<String> validaEtiquetasFormato(String token, String ubicacion) throws IOException {
        List<String> etiquetas = new ArrayList<>();
        etiquetas.add("#titulo#");
        etiquetas.add("#nombre#");
        etiquetas.add("#estudiante#");
        etiquetas.add("#director#");
        etiquetas.add("#resumen#");
        etiquetas.add("#ingles#");
        etiquetas.add("#introducción#");
        etiquetas.add("#contenido#");
        etiquetas.add("#conclusion#");
        for(String item : etiquetas){
            Optional<Boolean> valida = this.validoEtiqueta(ubicacion,item);
            if(valida.isPresent() && Boolean.FALSE.equals(valida.get())){
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    private Optional<Boolean> validoEtiqueta(String ubicacion, String etiqueta)throws IOException{
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(ubicacion)))){
            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
            //Iterate over paragraph list and check for the replaceable text in each paragraph
            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);
                    if(Objects.nonNull(docText) && docText.contains(etiqueta) ){
                        return Optional.of(Boolean.TRUE);
                    }
                }
            }
        }
        return Optional.of(Boolean.FALSE);
    }

    private Optional<ArticuloDto> getDataArticulo(String token,Long idArt){
        ResponseEntity<ArticuloDto> response = articuloCliente.getById(token, idArt);
        if(HttpStatus.OK.equals(response.getStatusCode()) && Objects.nonNull(response.getBody())){
            return Optional.of(response.getBody());
        }
        return Optional.empty();
    }

    private Optional<String> writeFileTemplate(XWPFDocument doc, Long idArt) throws IOException {
        String urlDestinoFinal = urlDestino + "articulo_"+ idArt + "_template.docx";
        try (FileOutputStream out = new FileOutputStream(urlDestinoFinal)) {
            doc.write(out);
        }
        return Optional.of(urlDestinoFinal);
    }

    private Optional<String> writeDocument(Long idArt, ArticuloDto art, String token) throws IOException{
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(urlFormato+templateName)))){
            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
            //Iterate over paragraph list and check for the replaceable text in each paragraph
            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);
                    docText = evaluateText(docText, art, token);
                    xwpfRun.setText(docText, 0);
                }
            }
            Optional<String> responseOut = writeFileTemplate(doc, idArt);
            return responseOut.isEmpty() ? Optional.empty() : responseOut;
        }
    }

    private String evaluateText(String texto, ArticuloDto articulo, String token){
        if( Objects.isNull(texto) || ( Objects.nonNull(texto) && !texto.contains("#") ) ){
            return texto;
        }
        if(texto.contains("#titulo#")){
            texto = texto.replace("#titulo#", articulo.getTitulo());
        }
        if(texto.contains("#nombre#")){
            Optional<IdeaDto> idea = ideaService.findById(token, articulo.getIdeaId());
            texto = texto.replace("#nombre#", idea.isPresent() ? idea.get().getNombreAlumno() : "");
        }
        if(texto.contains("#estudiante#")){
            Optional<IdeaDto> idea = ideaService.findById(token, articulo.getIdeaId());
            if(idea.isPresent()){
                Optional<UsuarioDto> usuario = usuarioService.getUserById(idea.get().getUsuarioId());
                if(usuario.isPresent()){
                    texto = texto.replace("#estudiante#", usuario.isPresent() ? usuario.get().getCodigo() : "");
                }
            }

        }
        if(texto.contains("#director#")){
            Optional<IdeaDto> idea = ideaService.findById(token, articulo.getIdeaId());
            if(idea.isPresent()){
                Optional<UsuarioDto> usuario = usuarioService.getUserById(idea.get().getIdProfesorAutoriza());
                if(usuario.isPresent()){
                    texto = texto.replace("#director#", usuario.isPresent() ? usuario.get().getPersona().getApellidos() + " " + usuario.get().getPersona().getNombres() : "");
                }
            }
        }
        if(texto.contains("#introducción#")){
            texto = texto.replace("#introducción#", articulo.getIntroduccion());
        }
        if(texto.contains("#resumen#")){
            texto = texto.replace("#resumen#", articulo.getResumen());
        }
        if(texto.contains("#ingles#")){
            texto = texto.replace("#ingles#", articulo.getResumen_ingles());
        }
        if(texto.contains("#conclusion#")){
            texto = texto.replace("#conclusion#", articulo.getConclusion());
        }
        if(texto.contains("#contenido#")){
            Optional<String> temp = generarContenidoParrafos(token, articulo.getId());
            texto = temp.isPresent() ? temp.get() : "";
        }
        return texto;
    }

    private Optional<String> generarContenidoParrafos(String token, Long id){
        StringBuilder responseString = new StringBuilder();
        Optional<List<ParrafoDto>> response = parrafoService.obtenerParrafosByIdArt(token, id);
        if(response.isPresent() && !response.get().isEmpty()){
            for(ParrafoDto item : response.get()){
                responseString.append(item.getContenido());
                responseString.append("\n");
            }
            return Optional.of(responseString.toString());
        }
        return Optional.empty();
    }
}