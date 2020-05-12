package kz.ktzh.repo;

import org.springframework.data.repository.CrudRepository;

import kz.ktzh.models.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
	UserInfo findByUsername(String username);
}
