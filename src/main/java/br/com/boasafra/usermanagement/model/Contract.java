package br.com.boasafra.usermanagement.model;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private double quantity;
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }
}
