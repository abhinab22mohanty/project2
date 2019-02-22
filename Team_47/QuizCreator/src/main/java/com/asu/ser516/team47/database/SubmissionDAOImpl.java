package com.asu.ser516.team47.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;

import java.util.List;
import java.util.Properties;

/**
 * An Submission Database Abstraction
 *
 * @author  Trevor Forrey
 * @version 1.0
 * @since   2/22/19
 */
public class SubmissionDAOImpl implements SubmissionDAO {
    // Hardcoded (will switch to loading through props in future)
    //    private static Properties __dbProperties;
    private static String __jdbcUrl = "jdbc:sqlite:schema.db";
    private static String __jdbcUser;
    private static String __jdbcPasswd;

    /**
     * Gets all submissions in the table
     * @return all submissions in the table
     */
    public List<Submission> getAllSubmissions() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Submission> rval = new ArrayList<Submission>();

        try {
            conn = DriverManager.getConnection(__jdbcUrl);

            stmt = conn.prepareStatement("select * from submissions");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rval.add(new Submission(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getDate(5), rs.getFloat(6),
                        rs.getInt(7)));
            }
        }
        catch (Exception se) {
            se.printStackTrace();
            return null;
        }
        finally {
            try {
                if (rs != null) { rs.close();}
                if (stmt != null) { stmt.close();}
                if (conn != null) { conn.close();}
            } catch (Exception e) { e.printStackTrace(); }
        }

        return rval;
    }

    /**
     * Gets all submissions for a quiz
     * @param quiz_fk quiz_id
     * @return all submissions for a quiz
     */
    public List<Submission> getQuizSubmissions(int quiz_fk) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Submission> rval = new ArrayList<Submission>();

        try {
            conn = DriverManager.getConnection(__jdbcUrl);
            stmt = conn.prepareStatement("select * from submissions where quiz_fk = ?");
            stmt.setInt(1, quiz_fk);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rval.add(new Submission(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getDate(5), rs.getFloat(6),
                        rs.getInt(7)));
            }
        }
        catch (Exception se) {
            se.printStackTrace();
            return null;
        }
        finally {
            try {
                if (rs != null) { rs.close();}
                if (stmt != null) { stmt.close();}
                if (conn != null) { conn.close();}
            } catch (Exception e) { e.printStackTrace(); }
        }

        return rval;
    }

    /**
     * Gets all submissions for an enrollment
     * @param enrolled_fk enrolled_id
     * @return all submissions for an enrollment
     */
    public List<Submission> getEnrolledSubmissions(int enrolled_fk) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Submission> rval = new ArrayList<Submission>();

        try {
            conn = DriverManager.getConnection(__jdbcUrl);

            stmt = conn.prepareStatement("select * from submissions where enrolled_fk = ?");
            stmt.setInt(1, enrolled_fk);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rval.add(new Submission(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getDate(5), rs.getFloat(6),
                        rs.getInt(7)));
            }
        }
        catch (Exception se) {
            se.printStackTrace();
            return null;
        }
        finally {
            try {
                if (rs != null) { rs.close();}
                if (stmt != null) { stmt.close();}
                if (conn != null) { conn.close();}
            } catch (Exception e) { e.printStackTrace(); }
        }

        return rval;
    }

    /**
     * Gets a submission based on it's submission_id
     * @param submission_id the id of the submission_id
     * @return a submission with the submission_id
     */
    public Submission getSubmission(int submission_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Submission rval = null;

        try {
            conn = DriverManager.getConnection(__jdbcUrl);

            stmt = conn.prepareStatement("select * from submissions where submission_id = ?");
            stmt.setInt(1, submission_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rval = new Submission(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getDate(5), rs.getFloat(6),
                        rs.getInt(7));
            }
        }
        catch (Exception se) {
            se.printStackTrace();
            return null;
        }
        finally {
            try {
                if (rs != null) { rs.close();}
                if (stmt != null) { stmt.close();}
                if (conn != null) { conn.close();}
            } catch (Exception e) { e.printStackTrace(); }
        }

        return rval;
    }

    /**
     * Inserts a submission in the database based on the
     * values in a business object
     * @param submission
     * @return a boolean representing a successful/failed insert
     */
    public int insertSubmission(Submission submission) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rval = 0;

        try {
            conn = DriverManager.getConnection(__jdbcUrl);

            stmt = conn.prepareStatement("insert into submissions (quiz_fk, enrolled_fk," +
                    " time_taken, date_taken, score, attempt) VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, submission.getQuiz_fk());
            stmt.setInt(2, submission.getEnrolled_fk());
            stmt.setInt(3, submission.getTime_taken());
            stmt.setDate(4, new java.sql.Date(submission.getDate_taken().getTime()));
            stmt.setDouble(5, submission.getScore());
            stmt.setInt(6, submission.getAttempt());
            int updatedRows = stmt.executeUpdate();
            if (updatedRows <= 0) {
                return 0;
            }

            // Return SQLite generated id of inserted value
            stmt = conn.prepareStatement("SELECT last_insert_rowid()");
            rs = stmt.executeQuery();
            while (rs.next()) {
                rval = rs.getInt(1);
            }
            return rval;
        }
        catch (Exception se) {
            se.printStackTrace();
            return 0;
        }
        finally {
            try {
                if (rs != null) { rs.close();}
                if (stmt != null) { stmt.close();}
                if (conn != null) { conn.close();}
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    /**
     * Updates a submission in the database based on the
     * values in a business object
     * @param submission a submission to update in the database
     * @return a boolean representing a successful/failed update
     */
    public boolean updateSubmission(Submission submission) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(__jdbcUrl);

            stmt = conn.prepareStatement("update submissions set quiz_fk=?, enrolled_fk=?, time_taken=?," +
                    " date_taken=?, score=?, attempt=? where submission_id=?");
            stmt.setInt(1, submission.getQuiz_fk());
            stmt.setInt(2, submission.getEnrolled_fk());
            stmt.setInt(3, submission.getTime_taken());
            stmt.setDate(4, new java.sql.Date(submission.getDate_taken().getTime()));
            stmt.setDouble(5, submission.getScore());
            stmt.setInt(6, submission.getAttempt());
            stmt.setInt(7, submission.getSubmission_id());
            int updatedRows = stmt.executeUpdate();
            if (updatedRows > 0) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception se) {
            se.printStackTrace();
            return false;
        }
        finally {
            try {
                if (rs != null) { rs.close();}
                if (stmt != null) { stmt.close();}
                if (conn != null) { conn.close();}
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    /**
     * Deletes a submission in the database based on the
     * values in a business object
     * @param submission a submission to delete in the database
     * @return a boolean representing a successful/failed deletion
     */
    public boolean deleteSubmission(Submission submission) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(__jdbcUrl);
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("delete from submissions where submission_id=?");
            stmt.setInt(1, submission.getSubmission_id());
            stmt.executeUpdate();
            // TODO (DELETE BEFORE SUBMISSION) May need to manually delete foreign keys in Answers
            conn.commit();
            return true;
        }
        catch (Exception se) {
            se.printStackTrace();
            return false;
        }
        finally {
            try {
                if (rs != null) { rs.close();}
                if (stmt != null) { stmt.close();}
                if (conn != null) { conn.close();}
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    static {
        try {
//            __dbProperties = new Properties();
//            __dbProperties.load(SubmissionDAOImpl.class.getClassLoader().getResourceAsStream("database.properties"));
//            __jdbcUrl    = __dbProperties.getProperty("jdbcUrl");
//            __jdbcUser   = __dbProperties.getProperty("jdbcUser");
//            __jdbcPasswd = __dbProperties.getProperty("jdbcPasswd");
//            __jdbcDriver = __dbProperties.getProperty("jdbcDriver");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
