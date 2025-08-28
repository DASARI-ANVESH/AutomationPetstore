import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Locale.Category;

import javax.swing.text.html.HTML.Tag;
public class Pet {
private Long id;
private Category category;
private String name;
@JsonProperty("photoUrls")
private List<String> photoUrls;
private List<Tag> tags;
private String status;
// Constructors
public Pet() {}
public Pet(String name, String status) {
this.name = name;
this.status = status;
}
// Getters and Setters
public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;

}
public Category getCategory() {
return category;
}
public void setCategory(Category category) {
this.category = category;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public List<String> getPhotoUrls() {
return photoUrls;
}
public void setPhotoUrls(List<String> photoUrls) {
this.photoUrls = photoUrls;
}
public List<Tag> getTags() {
return tags;
}
public void setTags(List<Tag> tags) {
this.tags = tags;
}
public String getStatus() {
return status;
}
public void setStatus(String status) {
this.status = status;
}
}
public static class Tag {
private Long id;
private String name;
// Getters and Setters
public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}
pubblic String getName() {
return name;
}
public void setName(String name) {
this.name =name;
}
}

