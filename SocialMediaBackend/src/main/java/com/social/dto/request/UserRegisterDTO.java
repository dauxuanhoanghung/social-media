package com.social.dto.request;

import com.social.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 *
 * @author LENOVO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO extends UserDTO {

    protected String password;
    
    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s", this.email, this.password, this.displayName, this.role);
    }
}
