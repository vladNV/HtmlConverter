package model.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file")
public class File {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "data_created", nullable = false)
    private LocalDateTime created;

    @Column(name = "sizeKB")
    private Long sizeKb;

    @Column(name = "file_type", nullable = false)
    private FileType type;
}
