INSERT INTO member (id, email, role, provider, follow_count, follow_member_count)
VALUES (1, 'hyh9510@nate.com', 'ADMIN', 'KAKAO', 0, 0);
INSERT INTO member_profile (name, birthday, gender, phone, nickname, thumbnail, member_id)
VALUES ('한창희', '1999-01-07', 'M', '01012345678', '송눈섭', 'https://www.naver.com', 1);
INSERT INTO member (id, email, role, provider, follow_count, follow_member_count)
VALUES (2, 'leech@gmail.com', 'ADMIN', 'KAKAO', 0, 0);
INSERT INTO member_profile (name, birthday, gender, phone, nickname, thumbnail, member_id)
VALUES ('이찬호', '1999-01-29', 'M', '01012345678', '리자노', 'https://www.naver.com', 2);
INSERT INTO category (id, name)
VALUES (1, '대학교');
INSERT INTO place (name, coordinates, thumbnail, address_province, address_city, address_district, address_detail,
                   road_address, description, place_search_provider)
VALUES ('강남대학교', ST_SetSRID(ST_MakePoint(37.27574, 127.13249), 4326), null, '경기도', '용인시', '기흥구', '구갈동 111, 강남대학교',
        '경기 용인시 기흥구 강남로 40 강남대학교',
        null, 'MEMBER');
