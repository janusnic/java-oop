package vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Brand {
       private int brandId;
       private String nameBrand;
       
       public Brand() {
            
        }
       
       public Brand(ResultSet rs) throws SQLException {
            setBrandId(rs.getInt(1));
            setNameBrand(rs.getString(2));
        }

  
        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getNameBrand() {
            return nameBrand;
        }

        public void setNameBrand(String nameBrand) {
            this.nameBrand = nameBrand;
        }

        public String toString() {
            return nameBrand;
        }
}
