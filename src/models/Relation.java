package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "relations")
@NamedQueries({
    @NamedQuery(
            name = "getMyAllFollowers",
            query = "SELECT r FROM Relation AS r WHERE r.follower = :follower"
            ),
    @NamedQuery(
            name = "getFollow",
            query = "SELECT r.followered FROM Relation AS r WHERE r.follower = :follower"
            ),
@NamedQuery(
        name = "checkFollow",
        query = "SELECT r FROM Relation AS r WHERE r.follower = :follower AND r.followered = :followered"
        ),
@NamedQuery(
        name = "getAllFollowered",
        query = "SELECT r FROM Relation AS r ORDER BY r.id DESC"
        )

})

@Entity
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followered_id", nullable = false)
    private User followered;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowered() {
        return followered;
    }

    public void setFollowered(User followered) {
        this.followered = followered;
    }



}
