package community_project.main.controller.Form;

import lombok.Data;

@Data
public class NoLoginCommentForm {
    String content, name, password;

    public NoLoginCommentForm(String content, String name, String password) {
        this.content = content;
        this.name = name;
        this.password = password;
    }
}
