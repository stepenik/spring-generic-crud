package illusive.networks.controller;

import illusive.networks.dto.BaseDTO;
import illusive.networks.service.CrudFacade;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

/**
 * Created by alon on 1/2/2020.
 */
public abstract class CrudController<DTO extends BaseDTO> {

    protected final CrudFacade<DTO> facade;

    protected CrudController(CrudFacade<DTO> facade) {
        this.facade = facade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<DTO> list() {
        return facade.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DTO get(@PathVariable(value = "id") UUID id) {
        return facade.find(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UUID create(@RequestBody DTO dto) {
        return facade.create(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") UUID id, @RequestBody DTO dto) {
        facade.edit(id, dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") UUID id) {
        facade.delete(id);
    }

}
