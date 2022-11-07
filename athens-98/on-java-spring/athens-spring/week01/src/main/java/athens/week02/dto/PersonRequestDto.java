package athens.week02.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PersonRequestDto {
    private String name;
    private int age;
    private String address;

    public PersonRequestDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public PersonRequestDto(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

}
