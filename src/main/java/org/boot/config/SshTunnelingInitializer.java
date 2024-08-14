package org.boot.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Component
@Configuration
@Validated
@Setter
public class SshTunnelingInitializer {
    @Value("${spring.ssh.remote_jump_host}")
    private String remoteJumpHost;
    @Value("${spring.ssh.ssh_port}")
    private int sshPort;
    @Value("${spring.ssh.user}")
    private String user;
    @Value("${spring.ssh.private_key_path}")
    private String privateKeyPath;
    @Value("${spring.ssh.database_port}")
    private int databasePort;
    private Session session;

    @PreDestroy
    public void destroy(){
        if(session.isConnected()){
            session.disconnect();
        }
    }

    public Integer buildSshConnection(){
        System.out.println(22222);
        Integer forwardPort = null;
        try {
            System.out.println(333333);
            log.info("Connecting to SSH woth {}@{}:{} using privateKey at {}",user , remoteJumpHost,sshPort,privateKeyPath);
            JSch jsch = new JSch();
            System.out.println(44444);
            System.out.println(user);
            System.out.println(remoteJumpHost);
            System.out.println(sshPort);
            System.out.println(privateKeyPath);
            jsch.addIdentity(privateKeyPath);
            System.out.println(5555555);
            session = jsch.getSession(user,remoteJumpHost,sshPort);
            System.out.println(666666);
            session.setConfig("StrictHostKeyChecking","no");
            System.out.println(777777);
            log.info("Starting SSH session connection...");
            session.connect();
            System.out.println(888888);
            log.info("SSH session connected");

            forwardPort = session.setPortForwardingL(0,"localhost",databasePort);
            log.info("Port forwarding created on local port {} to remote port {}",forwardPort,databasePort);
        } catch (JSchException e) {
            java.util.Properties config = new java.util.Properties();
            config.put("LogLevel", "DEBUG");
            session.setConfig(config);

            log.error(e.getMessage());
            this.destroy();
            throw new RuntimeException(e);
        }
        System.out.println(999999999);
        System.out.println(forwardPort);
        return forwardPort;
    }

}
