package model.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file")
public class File {
    @Id
    @Column(name = "fileId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "data_created", nullable = false)
    private LocalDateTime created;

    @Column(name = "sizeKB")
    private Long sizeKb;

    @Column(name = "file_type", nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public File setType(String type) {
        this.type = type;
        return this;
    }

    public String getPath() {
        return path;
    }

    public File setPath(String path) {
        this.path = path;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public File setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public Long getSizeKb() {
        return sizeKb;
    }

    public File setSizeKb(Long sizeKb) {
        this.sizeKb = sizeKb;
        return this;
    }

    @Override
    public String toString() {
        return "File{" + "id=" + id +
                ", path='" + path + '\'' +
                ", created=" + created +
                ", sizeKb=" + sizeKb +
                ", type=" + type +
                '}';
    }
}
