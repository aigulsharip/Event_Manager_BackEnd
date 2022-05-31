package kz.daracademy.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsModel {

    private String email;
    private String id;
    private List<String> roles;
    private String userId;
    private HashMap<String, ArrayList<String>> acl;

}
