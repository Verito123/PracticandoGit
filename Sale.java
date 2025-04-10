package com.mitocode.model;

import com.mitocode.dto.ProcedureDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@SqlResultSetMapping(
        name = "Procedure.ProcedureDTO",
        classes = @ConstructorResult(targetClass = ProcedureDTO.class,
                columns = {
                        @ColumnResult(name = "quantityfn", type = Integer.class),
                        @ColumnResult(name = "datetimefn", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "Sale.fn_sales", //un alias de configuracion
        query = "select * from fn_sales()",
        resultSetMapping = "Procedure.ProcedureDTO"
)

////////////////////////////////////
@NamedStoredProcedureQuery(
        name = "callPrSales", //un alias a la configuracion
        procedureName = "pr_sale",
        resultClasses = ProcedureDTO.class
)

@NamedStoredProcedureQuery(
        name = "getPrSales2",
        procedureName = "pr_sales2",
        resultClasses = ProcedureDTO.class,
        parameters = {
                @StoredProcedureParameter(name = "p_id_client", type = Integer.class, mode = ParameterMode.IN) //,
                //@StoredProcedureParameter(name = "ABC", type = List.class, mode = ParameterMode.REF_CURSOR),
                //@StoredProcedureParameter(name = "DEF", type = String.class, mode = ParameterMode.OUT),
        }
)


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "FK_SALE_CLIENT"))
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_SALE_USER"))
    private User user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double total;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double tax;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL) //, fetch = FetchType.LAZY)
    private List<SaleDetail> details;

    //ACID Atomicidad
    //Consistencia
    //Aislamiento
    //Durabilidad

}
