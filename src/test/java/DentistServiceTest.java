import dao.ConfigurationJDBC;
import dao.impl.DentistDaoImpl;
import model.Dentist;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.DentistService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DentistServiceTest {

    private static final DentistService dentistService = new DentistService(new DentistDaoImpl(new ConfigurationJDBC()));

    @BeforeAll
    public static void beforeAll(){
        dentistService.register(new Dentist(1245,"Mary","Jane"));
        dentistService.register(new Dentist(1641,"Peter","Parker"));
        dentistService.register(new Dentist(1382,"Green","Goblin"));
    }

    @Test
    public void registerDentist(){
        Dentist dentist = dentistService.register(new Dentist(8445,"Uncle","Ben"));

        assertNotNull(dentist.getId());
    }

    @Test
    public void listAllDentists(){
        List<Dentist> dentistsList = dentistService.listAll();
        assertFalse(dentistsList.isEmpty());

    }

    @Test
    public void findDentist(){
        assertNull(dentistService.findById(100));
        assertNotNull(dentistService.findById(1));
    }

    @Test
    public void removeAndFindDentist(){
        dentistService.removeById(2);
        assertNull(dentistService.findById(2));
    }

}
