package com.d208.fitmily.domain.family.mapper;

import com.d208.fitmily.domain.exercise.entity.Exercise;
import com.d208.fitmily.domain.family.entity.Family;
import com.d208.fitmily.domain.user.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FamilyMapper {

    /**
     * 가족 생성
     */
    @Insert("INSERT INTO family (family_name, family_invite_code, family_people, family_created_at, family_updated_at) " +
            "VALUES (#{familyName}, #{familyInviteCode}, #{familyPeople}, #{familyCreatedAt}, #{familyUpdatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "familyId")
    void createFamily(Family family);

    /**
     * 초대 코드로 패밀리 조회
     */
    @Select("SELECT * FROM family WHERE family_invite_code = #{inviteCode}")
    @Results(id = "familyMap", value = {
            @Result(property = "familyId", column = "family_id"),
            @Result(property = "familyName", column = "family_name"),
            @Result(property = "familyInviteCode", column = "family_invite_code"),
            @Result(property = "familyPeople", column = "family_people"),
            @Result(property = "familyCreatedAt", column = "family_created_at"),
            @Result(property = "familyUpdatedAt", column = "family_updated_at")
    })
    Family findByInviteCode(@Param("inviteCode") String inviteCode);

    /**
     * 패밀리 인원 수 증가
     */
    @Update("UPDATE family SET family_people = family_people + 1, family_updated_at = NOW() WHERE family_id = #{familyId}")
    void incrementFamilyPeople(@Param("familyId") int familyId);

    /**
     * 사용자의 패밀리 ID 업데이트
     */
    @Update("UPDATE user SET family_id = #{familyId} WHERE user_id = #{userId}")
    void updateUserFamilyId(@Param("userId") int userId, @Param("familyId") int familyId);

    /**
     * 패밀리 ID로 패밀리 조회
     */
    @Select("SELECT * FROM family WHERE family_id = #{familyId}")
    @ResultMap("familyMap")  // 이미 정의된 매핑 재사용
    Family findById(@Param("familyId") int familyId);

    /**
     * 패밀리의 최대 순서 번호 조회
     */
    @Select("SELECT MAX(user_family_sequence) FROM user WHERE family_id = #{familyId}")
    Integer findMaxFamilySequence(@Param("familyId") int familyId);

    /**
     * 사용자의 패밀리 ID와 순서를 함께 업데이트
     */
    @Update("UPDATE user SET family_id = #{familyId}, user_family_sequence = #{sequence} WHERE user_id = #{userId}")
    void updateUserFamilyIdAndSequence(
            @Param("userId") int userId,
            @Param("familyId") int familyId,
            @Param("sequence") int sequence
    );

    /**
     * 패밀리 구성원 목록 조회
     */
    @Select("SELECT * FROM user WHERE family_id = #{familyId}")
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "loginId", column = "user_login_id"),
            @Result(property = "password", column = "user_pw"),
            @Result(property = "nickname", column = "user_nickname"),
            @Result(property = "birth", column = "user_birth"),
            @Result(property = "gender", column = "user_gender"),
            @Result(property = "familyId", column = "family_id"),
            @Result(property = "role", column = "user_role"),
            @Result(property = "zodiacName", column = "user_zodiac_name"),
            @Result(property = "familySequence", column = "user_family_sequence"),
            @Result(property = "createdAt", column = "user_created_at"),
            @Result(property = "updatedAt", column = "user_updated_at")
    })
    List<User> findFamilyMembers(@Param("familyId") int familyId);

    @Mapper
    public interface ExerciseMapper {
        @Select("SELECT * FROM exercise WHERE user_id = #{userId} AND DATE(exercise_created_at) = #{date}")
        @Results(id = "exerciseMap", value = {
                @Result(property = "exerciseId", column = "exercise_id"),
                @Result(property = "userId", column = "user_id"),
                @Result(property = "exerciseName", column = "exercise_name"),
                @Result(property = "exerciseTime", column = "exercise_time"),
                @Result(property = "exerciseCount", column = "exercise_count"),
                @Result(property = "exerciseCalories", column = "exercise_calories"),
                @Result(property = "exerciseCreatedAt", column = "exercise_created_at"),
                @Result(property = "exerciseUpdatedAt", column = "exercise_updated_at")
        })
        List<Exercise> findUserExercisesByDate(@Param("userId") int userId, @Param("date") String date);

        @Select("SELECT COALESCE(SUM(exercise_calories), 0) FROM exercise WHERE user_id = #{userId} AND DATE(exercise_created_at) = #{date}")
        int calculateUserTotalCalories(@Param("userId") int userId, @Param("date") String date);
    }



    //
    /**
     * 가족 구성원 확인
     */
    @Select("SELECT COUNT(*) FROM user WHERE family_id = #{familyId} AND user_id = #{userId}")
    boolean checkFamilyMembership(@Param("familyId") String familyId, @Param("userId") String userId);

    /**
     * 가족 구성원 수 조회
     */
    @Select("SELECT COUNT(*) FROM user WHERE family_id = #{familyId}")
    int countFamilyMembers(@Param("familyId") String familyId);

    /**
     * 가족 구성원 ID 목록 조회
     */
    @Select("SELECT user_id FROM user WHERE family_id = #{familyId}")
    List<String> selectFamilyMemberIds(@Param("familyId") String familyId);


    @Select("SELECT family_id FROM user WHERE user_id = #{userId}")
    Integer selectFamilyIdByUserId(@Param("userId") Integer userId);


}

///**
// * 가족 구성원 확인
// */
//@Select("SELECT COUNT(*) FROM user WHERE family_id = #{familyId} AND user_id = #{userId}")
//boolean checkFamilyMembership(@Param("familyId") String familyId, @Param("userId") String userId);
//
///**
// * 가족 구성원 수 조회
// */
//@Select("SELECT COUNT(*) FROM user WHERE family_id = #{familyId}")
//int countFamilyMembers(@Param("familyId") String familyId);
//
///**
// * 가족 구성원 ID 목록 조회
// */
//@Select("SELECT user_id FROM user WHERE family_id = #{familyId}")
//List<String> selectFamilyMemberIds(@Param("familyId") String familyId);