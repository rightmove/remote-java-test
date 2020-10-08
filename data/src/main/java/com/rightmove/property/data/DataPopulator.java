package com.rightmove.property.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Profile("!test")
@Component
public class DataPopulator {

	private final PropertyDao propertyDao;

	@Autowired
	public DataPopulator(PropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}

	@PostConstruct
	public void insertPropertyData() {
		propertyDao.save(new PropertyEntity(1L,1000000L,7,2,"12","Richard Lane","London","W1F 3FT",PropertyType.DETACHED));
		propertyDao.save(new PropertyEntity(2L,100000L,2,1,"22","Brick Road","Sheffield","SH1 1AW",PropertyType.TERRACED));
		propertyDao.save(new PropertyEntity(3L,225000L,5,0,"40","Yellow Lane","Manchester","MA12 3ZY",PropertyType.DETACHED));
		propertyDao.save(new PropertyEntity(4L,150000L,1,1,"3B","Red Admiral Court","Essex","RM2 6ET",PropertyType.FLAT));
		propertyDao.save(new PropertyEntity(5L,222250L,3,1,"36","Bear Road","Winchester","WI3 9TT",PropertyType.DETACHED));
		propertyDao.save(new PropertyEntity(6L,750000L,10,4,"","Country House","Surrey","GU13 9DD",PropertyType.SEMI_DETACHED));
		propertyDao.save(new PropertyEntity(7L,125000L,2,2,"44","New Road","London","W1F 4DQ",PropertyType.SEMI_DETACHED));
		propertyDao.save(new PropertyEntity(8L,545444L,4,1,"55","Straight Road","Sheffield","SH1 8FG",PropertyType.TERRACED));
		propertyDao.save(new PropertyEntity(9L,574833L,4,3,"23","Curved Lane","Manchester","MA12 3AS",PropertyType.DETACHED));
		propertyDao.save(new PropertyEntity(10L,999999L,5,1,"101","Catherine House","Essex","RM2 0TY",PropertyType.FLAT));
		propertyDao.save(new PropertyEntity(11L,550000L,2,1,"15","Compton Avenue ","Winchester","WI3 7KL",PropertyType.DETACHED));
		propertyDao.save(new PropertyEntity(12L,7500000L,11,4,"","Brighton Road ","Surrey","GU13 4DD",PropertyType.SEMI_DETACHED));
		propertyDao.save(new PropertyEntity(13L,2500000L,7,2,"1","Station Road","London","W1F 3UT",PropertyType.SEMI_DETACHED));
		propertyDao.save(new PropertyEntity(14L,123000L,1,1,"22a","High Street","Sheffield","SH1 7AA",PropertyType.TERRACED));
		propertyDao.save(new PropertyEntity(15L,275000L,3,1,"78","The Approach ","Manchester","MA12 9DF",PropertyType.DETACHED));
		propertyDao.save(new PropertyEntity(16L,150000L,1,1,"Flat 12b","Windsor Court","Essex","RM2 7YA",PropertyType.FLAT));
		propertyDao.save(new PropertyEntity(17L,250000L,2,1,"23","Soho Road ","Winchester","WI3 7YI",PropertyType.DETACHED));
		propertyDao.save(new PropertyEntity(18L,755000L,4,0,"7","Park Lane","Surrey","GU13 3AS",PropertyType.SEMI_DETACHED));
		propertyDao.save(new PropertyEntity(19L,1010000L,6,6,"6","Value Road","London","W1F 3ER",PropertyType.SEMI_DETACHED));
		propertyDao.save(new PropertyEntity(20L,155000L,3,1,"1","Church Road","Sheffield","SH1 8KK",PropertyType.TERRACED));
		propertyDao.save(new PropertyEntity(21L,245000L,2,2,"40","Salmons Lane ","Manchester","MA12 4IJ",PropertyType.DETACHED));
		propertyDao.save(new PropertyEntity(22L,156000L,3,1,"9a","Red Admiral Court","Essex","RM2 6ET",PropertyType.FLAT));
		propertyDao.save(new PropertyEntity(23L,222550L,2,2,"90","Cedar Park","Winchester","WI3 2BO",PropertyType.DETACHED));
		propertyDao.save(new PropertyEntity(24L,755000L,5,5,"122","Jeff Street","Surrey","GU13 9OB",PropertyType.SEMI_DETACHED));
	}
}
