package co.edu.usb.Service;

import co.edu.usb.DTO.DestinationDTO;
import co.edu.usb.Utils.Constantes;
import co.edu.usb.Utils.Utilities;
import co.edu.usb.domain.Destination;
import co.edu.usb.domain.DestinationType;
import co.edu.usb.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Scope("singleton")
@Service
public class DestynationServiceImpl implements DestynationService {

    @Autowired
    private DestynationTypeService destynationTypeService;

    @Autowired
    private DestinationRepository destinationRepository;


    @Override
    public Destination guardarDestination(DestinationDTO destinationDTO) throws Exception {

        Destination destination = null;
        DestinationType destinationType = null;


        destinationType = destynationTypeService.findByCodeAndStatus(destinationDTO.getCodeDestinationType(), destinationDTO.getStatus());
        if (destinationType == null) {
            throw new Exception("El tipo destino " + destinationDTO.getCodeDestinationType() + " No existe");
        }

        Destination destinoDB = findByCode(destinationDTO.getCode().trim());
        if (destinoDB != null) {
            throw new Exception("Ya existe un destino con el mismo s codigo.");
        }

        if (destinationDTO.getAir() == null || destinationDTO.getAir().trim().equals("") ||
                Utilities.isStringLenght(destinationDTO.getAir(), Constantes.TamanoOViaje)
                || !Utilities.isStringInteger(destinationDTO.getAir())) {
            throw new Exception("Debe ingresar solo una S o una N en este campo");
        }

        if (destinationDTO.getLand() == null || destinationDTO.getLand().trim().equals("") ||
                Utilities.isStringLenght(destinationDTO.getLand(), Constantes.TamanoOViaje)
                || !Utilities.isStringInteger(destinationDTO.getLand())) {
            throw new Exception("Debe ingresar solo una S o una N en este campo");
        }

        if (destinationDTO.getSea() == null || destinationDTO.getSea().trim().equals("") ||
                Utilities.isStringLenght(destinationDTO.getSea(), Constantes.TamanoOViaje)
                || !Utilities.isStringInteger(destinationDTO.getSea())) {
            throw new Exception("Debe ingresar solo una S o una N en este campo");
        }

        if (destinationDTO.getName() == null || destinationDTO.getName().trim().equals("") ||
                !Utilities.isStringInteger(destinationDTO.getName())) {
            throw new Exception("Debe ingresar un nombre v??lido");
        }

        if (destinationDTO.getDescription() == null || destinationDTO.getDescription().trim().equals("")
                || destinationDTO.getDescription().trim().length() > 300) {
            throw new Exception("Debe ingresar una descripcion v??lida");
        }

        if (destinationDTO.getDateCreated() == null) {
            throw new Exception("Debe ingresar una fecha v??lia");
        }

        if (destinationDTO.getCreatorUser() == null || destinationDTO.getCreatorUser().trim().equals("")
                || destinationDTO.getCreatorUser().trim().length() > 10 ||
                !Utilities.isStringInteger(destinationDTO.getCreatorUser())) {
            throw new Exception("Debe ingresar un nombre de usuario v??lido:\n Recuerde: que no " +
                    "puede contener mas de 10 car??cteres");
        }

        if (destinationDTO.getModifierUser() == null || destinationDTO.getModifierUser().trim().equals("")
                || destinationDTO.getModifierUser().trim().length() > 10 ||
                !Utilities.isStringInteger(destinationDTO.getModifierUser())) {
            throw new Exception("Debe ingresar un usuario modificador v??lido:\n Recuerde: que no " +
                    "puede contener mas de 10 car??cteres");
        }

        if (destinationDTO.getStatus() == null || destinationDTO.getStatus().trim().equals("")
                || Utilities.isStringLenght(destinationDTO.getStatus(), Constantes.TamanoEstado)
                || !Utilities.isStringInteger(destinationDTO.getStatus())) {
            throw new Exception("DEBE INGRESAR UN ESTADO VALIDO:\n SOLO PUEDE INGRESAR " +
                    "A PARA ACTIVO O I PARA INACTIVO");
        }


        // crear el destino
        destination = new Destination();

        destination.setCode(destinationDTO.getCode());
        destination.setName(destinationDTO.getName());
        destination.setDescription(destinationDTO.getDescription());
        destination.setLand(destinationDTO.getLand());
        destination.setAir(destinationDTO.getAir());
        destination.setSea(destinationDTO.getSea());
        destination.setDateCreated(destinationDTO.getDateCreated());
        destination.setDateModified(destinationDTO.getDateModified());
        destination.setCreatorUser(destinationDTO.getCreatorUser());
        destination.setModifierUser(destinationDTO.getModifierUser());
        destination.setStatus(destinationDTO.getStatus());


        destination.setDestinationType(destinationType);

        destinationRepository.save(destination);

        return destination;
    }

    @Override
    public Destination findByCode(String Code) throws Exception {


        //Destination destination = null;
        Destination destination = destinationRepository.findByCode(Code);

        if (destination.getCode() == null || destination.getCode().trim().equals("")
                || !Utilities.isStringInteger(destination.getCode())) {
            throw new Exception("DEBE INGRESAR UN C??DIGO DE DESTINO V??LIDO");
        }
        return destination;
    }

    @Override
    public Destination updateDestination(DestinationDTO destinationDTO) throws Exception {

        Destination destination = null;
        DestinationType destinationType = null;


        if (destinationDTO.getIdDest() == null || destinationDTO.getIdDest().equals("")) {
            throw new Exception("Debe ingresar un destino v??lido para actualizar");
        }

        if (destinationDTO.getCode() == null || destinationDTO.getCode().trim().equals("")) {
            throw new Exception("Debe ingresar un codigo de destino v??lido");
        }
        if (destinationDTO.getAir() == null || destinationDTO.getAir().trim().equals("") ||
                Utilities.isStringLenght(destinationDTO.getAir(), Constantes.TamanoOViaje)
                || !Utilities.isStringInteger(destinationDTO.getAir())) {
            throw new Exception("Debe ingresar solo una S o una N en este campo");
        }

        if (destinationDTO.getLand() == null || destinationDTO.getLand().trim().equals("") ||
                Utilities.isStringLenght(destinationDTO.getLand(), Constantes.TamanoOViaje)
                || !Utilities.isStringInteger(destinationDTO.getLand())) {
            throw new Exception("Debe ingresar solo una S o una N en este campo");
        }

        if (destinationDTO.getSea() == null || destinationDTO.getSea().trim().equals("") ||
                Utilities.isStringLenght(destinationDTO.getSea(), Constantes.TamanoOViaje)
                || !Utilities.isStringInteger(destinationDTO.getSea())) {
            throw new Exception("Debe ingresar solo una S o una N en este campo");
        }

        if (destinationDTO.getName() == null || destinationDTO.getName().trim().equals("") ||
                !Utilities.isStringInteger(destinationDTO.getName())) {
            throw new Exception("Debe ingresar un nombre v??lido");
        }

        if (destinationDTO.getDescription() == null || destinationDTO.getDescription().trim().equals("")
                || destinationDTO.getDescription().trim().length() > 300) {
            throw new Exception("Debe ingresar una descripcion v??lida");
        }

        if (destinationDTO.getDateModified() == null || destinationDTO.getDateModified().equals("")) {
            throw new Exception("Debe ingresar una fecha de modificacion v??lida");
        }

        if (destinationDTO.getModifierUser() == null || destinationDTO.getModifierUser().trim().equals("")
                || destinationDTO.getModifierUser().trim().length() > 10 ||
                !Utilities.isStringInteger(destinationDTO.getModifierUser())) {
            throw new Exception("Debe ingresar un usuario modificador v??lido:\n Recuerde: que no " +
                    "puede contener mas de 10 car??cteres");
        }

        if (destinationDTO.getStatus() == null || destinationDTO.getStatus().trim().equals("")
                || Utilities.isStringLenght(destinationDTO.getStatus(), Constantes.TamanoEstado)
                || !Utilities.isStringInteger(destinationDTO.getStatus())) {
            throw new Exception("DEBE INGRESAR UN ESTADO VALIDO:\n SOLO PUEDE INGRESAR " +
                    "A PARA ACTIVO O I PARA INACTIVO");
        }


        // se busca el destino a actualizar
        destination = findById(destinationDTO.getIdDest());

        destination.setCode(destinationDTO.getCode());
        destination.setName(destinationDTO.getName());
        destination.setDescription(destinationDTO.getDescription());
        destination.setLand(destinationDTO.getLand());
        destination.setAir(destinationDTO.getAir());
        destination.setSea(destinationDTO.getSea());
        destination.setDateCreated(destinationDTO.getDateCreated());
        destination.setDateModified(destinationDTO.getDateModified());
        destination.setCreatorUser(destinationDTO.getCreatorUser());
        destination.setModifierUser(destinationDTO.getModifierUser());
        destination.setStatus(destinationDTO.getStatus());

        //buscar el tipo de destino
        destinationType = destynationTypeService.findByCodeAndStatus(destinationDTO.getCodeDestinationType(), destinationDTO.getStatus());

        if (destinationType == null ) {
            throw new Exception("El tipo destino " + destinationDTO.getCodeDestinationType() + " No existe");
        }

        destination.setDestinationType(destinationType);
        destinationRepository.save(destination);

        return destination;
    }

    @Override
    public Destination findById( Integer idDest) throws Exception {


        if (!destinationRepository.findById(idDest).isPresent()) {
            throw new Exception("El destino con id:  " + idDest + "\n" + "no existe ");
        }
        return destinationRepository.findById(idDest).get();
    }

    @Override
    public void DestinationDelete(Integer idDest) throws Exception {



        if(idDest == null || idDest.equals("") )
        {
            throw new Exception("Debe ingresar un id de destino v??lido");
        }
      if(!destinationRepository.findById(idDest).isPresent() ) {
            throw new Exception("El destino con id:  " + idDest + "" + " no existe ");
        }
        destinationRepository.deleteById(idDest);
    }


}


