USE selection_committee;

INSERT INTO 
	faculty (faculty_name, student_limit) 
VALUES 
	("Assasination faculty", 10),		-- 1
	("Star Traveling faculty", 2),		-- 2
	("Programming faculty", 9999),		-- 3
	("Magic faculty", 1),				-- 4
	("Architecture faculty", 7),		-- 5
	("BioEngineering faculty", 10),		-- 6
	("Surgery faculty", 50),			-- 7
	("Time Traveling faculty", 20),		-- 8
	("Draw and disign faculty", 30),	-- 9
	("Actors faculty", 44);				-- 10

INSERT INTO 
	subject (subject_name) 
VALUES 
	("Stealth"), 			-- 1
	("Actors Art"), 		-- 2
	("Fight"),				-- 3
	("Math"), 				-- 4
	("Phisics"), 			-- 5
	("Phisical training"), 	-- 6
	("Investigation"),		-- 7
	("Chemistry"), 			-- 8
	("Mind power"), 		-- 9
	("Biology"), 			-- 10
	("Drawing"), 			-- 11
	("Building"), 			-- 12
	("Imagination"), 		-- 13
	("Speaking"); 			-- 14

INSERT INTO 
	faculty_m2m_subject (faculty_id, subject_id) 
VALUES 
	(1, 1), (1, 2), (1, 3),
	(2, 5), (2, 6), (2, 7),
	(3, 4), (3, 5), (3, 7),
	(4,  9), (4,  13),
	(5, 5), (5, 11), (5, 12), (5, 13),
	(6, 8), (6, 10), (6, 4), (6, 5),
	(7, 7), (7, 8), (7, 10),
	(8, 6), (8, 7), (8, 13),
	(9, 11), (9, 13),
	(10, 2), (10, 14);

INSERT INTO 
	user (first_name, last_name, email, password, faculty_id, approved_status) 
VALUES 
	("Arya", "Stark", "nobody@braavos.com", MD5("Thewinteriscomming2"), 1, b'1'), 					-- 1
	("Jhon", "Snow", "jhon.s@nightwatch.com", MD5("kingOfNorth"), 2, b'1'),							-- 2
	("Cersei", "Lannister", "queen@kingslanding.com", MD5("Youlldiesoon1"), 3, b'1'),				-- 3
	("Hodor", "Hodor", "hodor@hodor.hodor", MD5("hodor"), 1, b'1'),									-- 4
	("Brandon", "Stark", "brandon.stark@winterfell.com", MD5("iknowwhatyoudidlastsummer"), 1, b'1'),-- 5
	("Teon", "Greyjoy", "bels@dinglebels.com", MD5("deadcantdie"), 1, b'1'),						-- 6
	("Bron", "Blackwater", "bron@deepa.com", MD5("yououghtmeacastle"), 1, b'0'),					-- 7
	("Eddar", "Start", "keeper@winterfell.com", MD5("goodmandeadman"), 4, b'1'),					-- 8
	("Davos", "Seaworth", "knight@dragonstone.com", MD5("knight"), 4, b'1'),						-- 9
	("Samwell", "Tarly", "sam@email.com", MD5("sam"), 7, b'1'),										-- 10
	("Tormund", "Giantsbase", "tormund@email.com", MD5("tormund"), 10, b'1'),						-- 11
	("Brienne", "Tarth", "brienne@email.com", MD5("brienne"), 9, b'1'),								-- 12
	("f_empty", "l_empty", "empty@email.com", MD5("empty"), null, b'1'),							-- 13
	("firstname_root", "lastname_root", "root@root", MD5("root"), null, b'1');						-- 14

INSERT INTO 
	user_role (user_id, role) 
VALUES 
	(1, "ROLE_USER"), 
	(2, "ROLE_USER"), 
	(3, "ROLE_USER"), (3, "ROLE_ADMIN"),
	(4, "ROLE_USER"),
	(5, "ROLE_USER"),
	(6, "ROLE_USER"),
	(7, "ROLE_USER"),
	(8, "ROLE_USER"),
	(9, "ROLE_USER"),
	(10, "ROLE_USER"), (10, "ROLE_ADMIN"),
	(11, "ROLE_USER"),
	(12, "ROLE_USER"),
	(13, "ROLE_USER"),
	(14, "ROLE_USER"), (14, "ROLE_ADMIN");

INSERT INTO 
	user_certificate (user_id, subject_id, score) 
VALUES 
	(1, 1, 10), (1, 2, 8), (1, 3, 9), (1, 6, 1), 
	(2, 5, 5), (2, 6, 10), (2, 7, 7),
	(3, 4, 9), (3, 5, 9), (3, 7, 11),
	(4, 1, 5), (4, 2, 5), (4, 3, 3),
	(5, 1, 9), (5, 2, 9),
	(6, 1, 3), (6, 2, 2), (6, 3, 2),
	(7, 1, 5), (7, 2, 7), (7, 3, 5),
	(8, 9, 7), (8, 13, 10), (8, 10, 2),
	(9, 9, 6), (9, 13, 8),
	(10, 7, 8), (10, 8, 8),
	(11, 2, 7), (11, 14, 7), (11, 4, 8),
	(12, 11, 5), (12, 10, 7), (12, 1, 1),
	(13, 1, 10), (13, 2, 10), (13, 3, 10), (13, 4, 10), (13, 5, 10), (13, 6, 10),
	(14, 1, 2), (14, 7, 3);
