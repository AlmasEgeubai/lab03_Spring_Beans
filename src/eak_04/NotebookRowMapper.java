package eak_04;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;

public class NotebookRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        PersonResultSetExtractor extractor = new PersonResultSetExtractor();
        return extractor.extractData(rs);
    }
    class PersonResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            notebook notebook = new notebook();
            notebook.setId(rs.getInt(1));
            notebook.setName(rs.getString(2));
            notebook.setCity(rs.getString(3));
            notebook.setNum(rs.getInt(4));
            return notebook;
        }
    }
}
