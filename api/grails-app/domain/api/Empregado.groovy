package api

class Empregado {

    string nome
    string sobrenome
    static belongsTo = [departamento: Departamento]

    static constraints = {
    }
}
