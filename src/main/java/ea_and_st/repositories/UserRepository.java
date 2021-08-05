package ea_and_st.repositories;

import com.ea_and_st.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndIsDeleted(Long id, boolean isDeleted);

    boolean existsByIdAndIsDeleted(Long id, boolean isDeleted);

    boolean existsByEmailAndIsDeleted(String email, boolean isDeleted);

    Optional<User> getUserByEmailAndIsDeleted(String email, boolean isDeleted);
}
