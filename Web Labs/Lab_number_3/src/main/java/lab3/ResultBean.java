package lab3;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Named
@ApplicationScoped
@Entity
@Table(name = "results", schema = "s367629")
public class ResultBean implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private String y;
    @Column(name = "r")

    private double r;
    @Column(name = "result")

    private Boolean result;
    @Column(name = "executedAt")
    private LocalDateTime executedAt;
    @Column(name = "executionTime")

    private long executionTime;



    public void processForm() {
        System.out.println("Значение X: " + x);
        System.out.println("Значение Y: " + y);
        System.out.println("Значение R: " + r);
    }

    @Column(name = "y")
    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    @Column(name = "r")
    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Column(name = "result")
    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Column(name = "x")

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Column(name = "executionTime")
    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    @Column(name = "executedAt")
    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

    @Override
    public String toString() {
        return "resultbean{" +
                " x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                ", executedAt=" + executedAt +
                ", execTime=" + executionTime +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "id")
    public Long getId() {
        return id;
    }
}
