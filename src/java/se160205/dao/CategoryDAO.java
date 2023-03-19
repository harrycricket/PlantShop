package se160205.dao;

import se160205.dto.Category;
import se160205.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh Van Phuot
 */
public class CategoryDAO {

    public static List<Category> getAllCategories() {
        Connection conn = null;
        List<Category> listCategories = new ArrayList<>();
        try {
            conn = DBUtils.makeConnection();
            String sql = "SELECT CateId, CateName FROM categories";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CateId");
                String name = rs.getString("CateName");
                listCategories.add(new Category(id, name));
            }
        } catch (Exception e) {
            listCategories.clear();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listCategories;
    }

    public static Category getCategory(int id) {
        Connection conn = null;
        Category category = new Category();
        try {
            conn = DBUtils.makeConnection();
            String sql = "SELECT CateName FROM categories where CateId = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("CateName");
                category.setId(id);
                category.setName(name);
            }
        } catch (Exception e) {
            category = null;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return category;
    }

    public static boolean saveCategory(Category category) {
        Connection conn = null;
        boolean result = true;
        try {
            conn = DBUtils.makeConnection();
            conn.setAutoCommit(false);

            String sql = "";

            if (category.getId() == 0) {
                sql = "INSERT INTO Categories(CateName) Values (?)";
            } else {
                sql = "UPDATE Categories SET CateName = ? WHERE CateId = ?";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, category.getName());

            if (category.getId() != 0) {
                stm.setInt(2, category.getId());
            }

            int rs = stm.executeUpdate();
            if (rs == 0) {
                result = false;
            } else {
                conn.commit();
            }

        } catch (Exception e) {
            category = null;
            try {
                conn.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
