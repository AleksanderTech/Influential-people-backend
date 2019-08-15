package com.alek.influentialpeople.user;

import com.alek.influentialpeople.article.articleComment.ArticleComment;
import com.alek.influentialpeople.article.domain.Article;
import com.alek.influentialpeople.hero.HeroScore;
import com.alek.influentialpeople.quote.Quote;
import com.alek.influentialpeople.user.role.Role;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "username", referencedColumnName = "username")}
            , inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;
    @Column(columnDefinition = "int default 0")
    private int activation;
    @OneToMany(mappedBy = "user")
    private Set<Article> articles;
    @OneToMany(mappedBy = "user")
    private Set<Quote> quotes;
    @OneToMany(mappedBy = "user")
    private Set<ArticleComment> articleComments;
    @OneToMany(mappedBy = "user")
    private Set<HeroScore> heroScores;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getActivation() {
        return activation;
    }

    public void setActivation(int activation) {
        this.activation = activation;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(Set<Quote> quotes) {
        this.quotes = quotes;
    }

    public Set<ArticleComment> getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(Set<ArticleComment> articleComments) {
        this.articleComments = articleComments;
    }

    public Set<HeroScore> getHeroScores() {
        return heroScores;
    }

    public void setHeroScores(Set<HeroScore> heroScores) {
        this.heroScores = heroScores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return activation == user.activation &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(articles, user.articles) &&
                Objects.equals(quotes, user.quotes) &&
                Objects.equals(articleComments, user.articleComments) &&
                Objects.equals(heroScores, user.heroScores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, roles, activation, articles, quotes, articleComments, heroScores);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", roles=").append(roles);
        sb.append(", activation=").append(activation);
        sb.append('}');
        return sb.toString();
    }
}
