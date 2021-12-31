package it.univpm.Model;

//per i tweet normali 
public class TweetPost {
    private String post_date;
    private String post_id;
    private String user_id;
    private String user_name;
    private String user_post_num;
    private String post_lang;
    private String post_type;
    private String location;
    private String[] user_post_mentions;
    private String[] post_hashtags;

    //COSTRUTTORI
    public TweetPost(){
        this.post_date = "";
        this.post_id = "";
        this.user_id = "";
        this.user_name = "";
        this.user_post_num = "";
        this.post_lang = "";
        this.post_type = "";
        this.location = "";
        this.user_post_mentions = null;
        this.post_hashtags = null;
    }

    public TweetPost(String postDate, String postID, String userID,String userName,String userPostNum,
                            String postLang,String postType, String location, String[] userPostMentions,String[] postHashtags){
        setPostDate(postDate);
        setPost_id(postID);
        setUser_id(userID);
        setUser_name(userName);
        setUser_post_num(userPostNum);
        setPost_lang(postLang);
        setPost_type(postType);
        setLocation(location);
        setUser_post_mentions(userPostMentions);
        setPost_hashtags(postHashtags);
    }

    public TweetPost(TweetPost tp){
        setPostDate(tp.getPostDate());
        setPost_id(tp.getPost_id());
        setUser_id(tp.getUser_id());
        setUser_name(tp.getUser_name());
        setUser_post_num(tp.getUser_post_num());
        setPost_lang(tp.getPost_lang());
        setPost_type(tp.getPost_type());
        setLocation(tp.getLocation());
        setUser_post_mentions(tp.getUser_post_mentions());
        setPost_hashtags(tp.getPost_hashtags());
    }

    //GETTER & SETTER
    public String getPost_id() {return post_id;}
    public void setPost_id(String post_id) {this.post_id = post_id;}

    public String getUser_id() {return user_id;}
    public void setUser_id(String user_id) {this.user_id = user_id;}

    public String getUser_name() {return user_name;}
    public void setUser_name(String user_name) {this.user_name = user_name;}

    public String getUser_post_num() {return user_post_num;}
    public void setUser_post_num(String user_post_num) {this.user_post_num = user_post_num;}

    public String getPost_lang() {return post_lang;}
    public void setPost_lang(String post_lang) {this.post_lang = post_lang;}

    public String getPost_type() {return post_type;}
    public void setPost_type(String post_type) {this.post_type = post_type;}

    public String[] getUser_post_mentions() {return user_post_mentions;}
    public String getUSer_post_mentions(String[] str, int id){return str[id];}
    public void setUser_post_mentions(String[] user_post_mentions) {this.user_post_mentions = user_post_mentions;}

    public String[] getPost_hashtags() {return post_hashtags;}
    public void setPost_hashtags(String[] post_hashtags) {this.post_hashtags = post_hashtags;}

    public void setPostDate(String post_date){this.post_date = post_date;}
    public String getPostDate(){return this.post_date;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}
}