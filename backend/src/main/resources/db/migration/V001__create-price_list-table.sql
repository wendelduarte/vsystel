CREATE TABLE price_list (
	id INT GENERATED ALWAYS AS IDENTITY,
	origin_area_code INT NOT NULL,
	target_area_code INT NOT NULL,
	price_per_minute decimal(10,2) NOT NULL
);

CREATE TABLE call_plans (
	id INT GENERATED ALWAYS AS IDENTITY,
	description varchar(100) NOT NULL,
	call_duration_limit INT NOT NULL
);

CREATE TABLE consultation_history (
	id INT GENERATED ALWAYS AS IDENTITY,
	origin_area_code INT NOT NULL,
	target_area_code INT NOT NULL,
	call_duration_limit INT NOT NULL,
	plan_description varchar(100) NOT NULL,
	price_with_plan decimal(10,2),
	price_without_plan decimal(10,2),
	date date NOT NULL
);
