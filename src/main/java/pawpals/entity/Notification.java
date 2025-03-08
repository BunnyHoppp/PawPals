package pawpals.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private String owner;
    private String sitter;
    private String number;
    private String start;
    private String end;

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + owner + '\'' +
                ", owner=" + sitter + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
