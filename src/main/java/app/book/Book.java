package app.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(value = "book_by_id")
public class Book {

    @Id
    @PrimaryKeyColumn(name = "book_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @Column("book_name")
    @CassandraType(type = Name.TEXT)
    private String name;

    @Column("book_description")
    @CassandraType(type = Name.TEXT)
    private String description;

    @Column("published_date")
    @CassandraType(type = Name.DATE)
    private LocalDate publishedDate;

    @Column("cover_ids")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> coverIds;

    @Column("author_names")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> authorNames;

    @Column("author_ids")
    @CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
    private List<String> authorIds;

}
