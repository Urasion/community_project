package community_project.main.repository.mapper;

import community_project.main.dto.MemberDto;
import community_project.main.dto.MemberInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface MemberMapper {
    void save(@Param("member") MemberDto member);


    MemberDto findById(@Param("id") String id);
    ArrayList<MemberDto> findByName(@Param("name") String name);
}
