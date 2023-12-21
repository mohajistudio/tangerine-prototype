INSERT INTO member (id, email, role, provider)
VALUES (1, 'hyh9510@nate.com', 'ADMIN', 'KAKAO');
INSERT INTO member_profile (birthday, sex, phone, nickname, thumbnail, member_id)
VALUES ('1999-01-07', 'M', '01012345678', '송눈섭', 'https://www.naver.com', 1);
INSERT INTO category (id, name) VALUES (1, '대학교')
INSERT INTO place (name, coordinates, thumbnail, address, roadAddress, description) VALUES ('강남대학교', Point(37.27574, 127.13249), null, '구갈동 111 강남대학교', '경기 용인시 기흥구 강남로 40 강남대학교', '')
