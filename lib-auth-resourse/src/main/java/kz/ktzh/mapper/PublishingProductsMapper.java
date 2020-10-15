package kz.ktzh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kz.ktzh.models.PublishingProducts;

public class PublishingProductsMapper implements RowMapper<PublishingProducts>{

	@Override
	public PublishingProducts mapRow(ResultSet rs, int rowNum) throws SQLException {
		PublishingProducts pp = new PublishingProducts();
		pp.setId(rs.getInt("id"));
		pp.setName(rs.getString("name"));		
		pp.setLink(rs.getString("link_photo"));
		return pp;
	}

}
