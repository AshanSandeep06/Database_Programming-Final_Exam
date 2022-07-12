package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private String student_id;
    private String student_name;
    private String email;
    private String contact;
    private String address;
    private String nic;
}
