package .

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DepartamentoController {

    DepartamentoService departamentoService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond departamentoService.list(params), model:[departamentoCount: departamentoService.count()]
    }

    def show(Long id) {
        respond departamentoService.get(id)
    }

    def save(Departamento departamento) {
        if (departamento == null) {
            render status: NOT_FOUND
            return
        }

        try {
            departamentoService.save(departamento)
        } catch (ValidationException e) {
            respond departamento.errors, view:'create'
            return
        }

        respond departamento, [status: CREATED, view:"show"]
    }

    def update(Departamento departamento) {
        if (departamento == null) {
            render status: NOT_FOUND
            return
        }

        try {
            departamentoService.save(departamento)
        } catch (ValidationException e) {
            respond departamento.errors, view:'edit'
            return
        }

        respond departamento, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        departamentoService.delete(id)

        render status: NO_CONTENT
    }
}
package api

import grails.gorm.services.Service

@Service(Departamento)
interface DepartamentoService {

    Departamento get(Serializable id)

    List<Departamento> list(Map args)

    Long count()

    void delete(Serializable id)

    Departamento save(Departamento departamento)

}