package org.boot.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SshDataSourceConfig {
    private final SshTunnelingInitializer initializer;

//    @Bean("dataSource")
//    @Primary
//    public DataSource dataSource(DataSourceProperties properties){
//        System.out.println(1111111);
//        Integer forwardedPort = initializer.buildSshConnection();
//        String url = properties.getUrl().replace("[forwardedPort]",String.valueOf(forwardedPort));
//        log.info(url);
//        return DataSourceBuilder.create()
//                .url(url)
//                .username(properties.getUsername())
//                .password(properties.getPassword())
//                .driverClassName(properties.getDriverClassName())
//                .build();
//    }


    private Session session;

    @Bean("dataSource")
    @Primary
    public DataSource dataSource(DataSourceProperties properties){
        String driver = "com.mysql.cj.jdbc.Driver";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String localhost ="127.0.0.1";
        int port = 3306;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession("ubuntu", "ec2-3-38-169-41.ap-northeast-2.compute.amazonaws.com", 22);
            //session.setPassword("SSH비번");
            System.out.println("SSH Connection...");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            int forward_port = session.setPortForwardingL(0, localhost, 3306);
            System.out.println("localhost: "+forward_port+" -> "+localhost+":"+port);
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mariadb://"+localhost+":"+forward_port+"/데이터베이스이름", "디비유저", "디비비번");
            if (conn != null) {
                System.out.println("DB 접속 성공 " + conn);
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
        return DataSourceBuilder.create()
        .url(properties.getUrl())
        .username(properties.getUsername())
        .password(properties.getPassword())
        .driverClassName(properties.getDriverClassName())
        .build();
    }

}
