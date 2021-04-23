package co.com.ud.business.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class NotificacionesControllerTest {

    private NotificacionesController notificacionesController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.notificacionesController = new NotificacionesController();
    }

    @Test
    public void testGetNotificacionesProfEMPTY(){
        ResponseEntity<Integer> response = this.notificacionesController.getNotificacionesProf(1L);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testNotificacionesAlumnEMPTY(){
        ResponseEntity<Integer> response = this.notificacionesController.getNotificacionesAlumn(1L);
        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}