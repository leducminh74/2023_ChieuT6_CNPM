INSERT INTO `status` (`s_id`, `s_name`) VALUES (0, 'Not Activated');
INSERT INTO `status` (`s_id`, `s_name`) VALUES (1, 'Activated');
INSERT INTO `status` (`s_id`, `s_name`) VALUES (2, 'Disable');

INSERT INTO `role` (`role_id`, `role_name`) VALUES (0, 'user');
INSERT INTO `role` (`role_id`, `role_name`) VALUES (1, 'admin');

INSERT INTO `account` (`user_id`, `email`, `password`, `username`, `role`, `status`) VALUES (13, 'ducminh1@gmail.com', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'admin', 1, 1);
INSERT INTO `account` (`user_id`, `email`, `password`, `username`, `role`, `status`) VALUES (18, '19130133@st.hcmuaf.edu.vn', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'user', 0, 1);