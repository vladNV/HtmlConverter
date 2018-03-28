package model.domain;

import javax.persistence.*;

@Entity
@Table(name = "mail")
public class Mail {

    @Id
    @Column(name = "IdMail")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Login")
    private String UserLogin;

    @Column(name = "FromEmail")
    private String from;

    @Column(name = "ToEmail")
    private String to;

    @Column(name = "MText")
    private String text;

    @Column(name = "MSubject")
    private String subject;

    public Long getId() {
        return id;
    }

    public String getUserLogin() {
        return UserLogin;
    }

    public Mail setUserLogin(String userLogin) {
        UserLogin = userLogin;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public Mail setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public Mail setTo(String to) {
        this.to = to;
        return this;
    }

    public String getText() {
        return text;
    }

    public Mail setText(String text) {
        this.text = text;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Mail setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public String toString() {
        return "Mail{" + "id=" + id +
                ", UserLogin='" + UserLogin + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
