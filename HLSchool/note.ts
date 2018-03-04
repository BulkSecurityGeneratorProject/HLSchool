<li class="nav-item" routerLink="comment" routerLinkActive="active" [routerLinkActiveOptions]="{exact: true}">

mail:
        host: smtp.gmail.com
        port: 587
        username: luantm96@gmail.com
        password: Dienvi96
        protocol: smtp
        tls: true
        properties.mail.smtp:
            auth: true
            starttls.enable: true
            ssl.trust: smtp.gmail.com