package kz.ktzh.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kz.ktzh.mapper.PublishingProductsMapper;
import kz.ktzh.models.PublishingProducts;

@Repository
public class PublishingProductsDAO {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<PublishingProducts> getPubProdList(){
		String sql = "SELECT izdatel_produkt.id, izdatel_produkt.name, izdatel_photo.link_photo FROM izdatel_produkt "
				+ "LEFT JOIN izdatel_photo ON izdatel_photo.id = izdatel_produkt.id";
		List<PublishingProducts> ppList = jdbcTemplate.query(sql, new PublishingProductsMapper());
		return ppList;		
	}

}
