/**
 *
 * @author Nicolás Zhao (100%)
 */
package trabajoTAW.dto;

import java.util.List;
import java.util.Objects;
//import trabajoTAW.entity.Usuario;


public class ListaUsuarioDTO {
    
    private Integer idListaUsuario;
    private String nombre;

    public ListaUsuarioDTO() {
    }


    public Integer getIdListaUsuario() {
        return idListaUsuario;
    }

    public void setIdListaUsuario(Integer idListaUsuario) {
        this.idListaUsuario = idListaUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idListaUsuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListaUsuarioDTO other = (ListaUsuarioDTO) obj;
        if (!Objects.equals(this.idListaUsuario, other.idListaUsuario)) {
            return false;
        }
        return true;
    }
}

