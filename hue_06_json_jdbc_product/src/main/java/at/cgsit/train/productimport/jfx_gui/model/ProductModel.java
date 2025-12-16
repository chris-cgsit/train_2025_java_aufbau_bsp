package at.cgsit.train.productimport.jfx_gui.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.*;
import java.math.BigDecimal;
import java.time.Instant;

// Diese Klasse dient als Daten-Model und als FX-Model
public class ProductModel {

    private final LongProperty id;
    private final StringProperty name;
    private final ObjectProperty<BigDecimal> price; // ObjectProperty für BigDecimal
    private final BooleanProperty active;
    private final ObjectProperty<Instant> createdAt; // ObjectProperty für Instant

    // Standard-Konstruktor (für Jackson-Deserialisierung)
    public ProductModel() {
        this(0, null, null, false, null);
    }

    // Haupt-Konstruktor
    public ProductModel(long id, String name, BigDecimal price, boolean active, Instant createdAt) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleObjectProperty<>(price);
        this.active = new SimpleBooleanProperty(active);
        this.createdAt = new SimpleObjectProperty<>(createdAt);
    }

    // --- Getter für Properties (wichtig für Binding/CellFactories) ---
    public LongProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public ObjectProperty<BigDecimal> priceProperty() { return price; }
    public BooleanProperty activeProperty() { return active; }
    public ObjectProperty<Instant> createdAtProperty() { return createdAt; }

    // --- Standard Getter (optional, aber nützlich) ---
    public long getId() { return id.get(); }
    public String getName() { return name.get(); }
    public BigDecimal getPrice() { return price.get(); }
    public boolean isActive() { return active.get(); }
    public Instant getCreatedAt() { return createdAt.get(); }

    // --- Standard Setter (wichtig für Jackson) ---
    // @JsonProperty wird in diesem Fall am Setter oder direkt am Feld verwendet.
    @JsonProperty("id")
    public void setId(long id) { this.id.set(id); }
    // ... andere Setter weggelassen, da der Haupt-Konstruktor oft bevorzugt wird
    @JsonProperty("created_at")
    public void setCreatedAt(Instant createdAt) { this.createdAt.set(createdAt); }
}