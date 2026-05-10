package com.ghas.chaekgalpi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "novels")
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    private String nameEng;
    private String nameKor;
    private String author;
    private Integer chapter;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String resenha;
    @Column
    private Integer rating;
    @Lob
    @Column(columnDefinition = "TEXT") // Ou "LONGTEXT" se for MySQL
    private String cover;

    @Enumerated(EnumType.STRING)
    private NovelStatus status;

    private Boolean favorito;

    private LocalDateTime dataAdicao;
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        dataAdicao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    public Novel(){}

    public Novel(Long id, Usuario usuario, String nameEng, String nameKor, String author,Integer chapter,
                 NovelStatus status,
                 Boolean favorito,
                 LocalDateTime dataAtualizacao, LocalDateTime dataAdicao) {
        this.id = id;
        this.usuario = usuario;
        this.nameEng = nameEng;
        this.nameKor = nameKor;
        this.author = author;
        this.chapter = chapter;
        this.status = status;
        this.favorito = favorito;
        this.dataAtualizacao = dataAtualizacao;
        this.dataAdicao = dataAdicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameKor() {
        return nameKor;
    }

    public void setNameKor(String nameKor) {
        this.nameKor = nameKor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getChapter() {
        return chapter;
    }

    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }

    public NovelStatus getStatus() {
        return status;
    }

    public void setStatus(NovelStatus status) {
        this.status = status;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public LocalDateTime getDataAdicao() {
        return dataAdicao;
    }

    public void setDataAdicao(LocalDateTime dataAdicao) {
        this.dataAdicao = dataAdicao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
