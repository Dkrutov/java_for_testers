package manager;

import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");
        var statement = conn.createStatement();
        var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
        {
        while (result.next()) {
            groups.add(new GroupData()
                    .withId(result.getString("group_id"))
                    .withName(result.getString("group_name"))
                    .withHeader(result.getString("group_header"))
                    .withFooter(result.getString("group_footer")));
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        try ( var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");
              var statement = conn.createStatement();
              var result = statement.executeQuery("SELECT * FROM address_in_groups ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL"))
        {
            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void testQuerySelect() {
        try ( var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");
              var statement = conn.createStatement();
              var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
        {
            while (result.next()) {
                var group_id = result.getString("group_id");
                System.out.print(group_id + " | ");
                var group_name = result.getString("group_name");
                System.out.print(group_name + " | ");
                var group_header = result.getString("group_header");
                System.out.print(group_header + " | ");
                var group_footer = result.getString("group_footer");
                System.out.print(group_footer + " | ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void testQueryUpdate() {
        try {
            var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");
            var statement = conn.createStatement();
            var result = statement.executeUpdate("UPDATE group_list SET group_name = 'test2' WHERE group_id = 295");

            System.out.println("Обновление успешно: result = " + result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void testQueryInsert() {
        try {
            var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");
            var statement = conn.createStatement();
            var result = statement.executeUpdate("INSERT group_list (domain_id, group_parent_id, created, modified, deprecated, group_name, group_header, group_footer ) VALUES (0,null,null,null,NOW(),'test3','test3','test3')");

            System.out.println("Добавление успешно: result = " + result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
