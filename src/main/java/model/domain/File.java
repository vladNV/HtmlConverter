package model.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file")
public class File {
    @Id
    @Column(name = "fileId")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Long getSizeKb() {
        return sizeKb;
    }

    public void setSizeKb(Long sizeKb) {
        this.sizeKb = sizeKb;
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
