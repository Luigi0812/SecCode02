--
-- Populate DB with initial data
--

INSERT INTO user (id, login, password, name) VALUES (1, 'john', 'C2x6mZ', 'John Doe');
INSERT INTO user (id, login, password, name) VALUES (2, 'mary', 'vM37qb', 'Mary Glenn');
INSERT INTO user (id, login, password, name) VALUES (3, 'bill', 'g44xED', 'Bill Sutton');
INSERT INTO user (id, login, password, name) VALUES (4, 'anna', 'X3wj4q', 'Anna Smith');

INSERT INTO article (id, title, lead, text) VALUES (1, 'Tumblr boosts security with two-factor authentication', 'In an effort to deter hackers, the blogging platform introduces added security that requires users to log-in with both a password and cell phone code.', 'Tumblr has joined the ranks of Google, Apple, Microsoft, Twitter, Dropbox, and many others in beefing up users'' online security with two-factor authentication. The blogging platform announced on Monday that it has launched the added security measure via users'' Settings page. Now, Tumblr bloggers have the option of making it more difficult for outside actors to access their dashboards.');
INSERT INTO article (id, title, lead, text) VALUES (2, 'Symantec fires CEO Steve Bennett', 'Board member Michael Brown will serve as interim CEO while maker of Norton antivirus software hunts for its second chief executive in two years.', 'After less than two years in the top job at Symantec, Steve Bennett has been fired. The security software firm announced Thursday that Bennett had been terminated as the company''s president and CEO, and he has resigned from the company''s board of directors. Board member Michael Brown will take over as interim president and CEO, effective immediately.');
INSERT INTO article (id, title, lead, text) VALUES (3, 'Beware this big iOS flaw -- and it''s not alone', 'Mobile security remains a struggle: CanSecWest calls attention to flaws in Android and BlackBerry 10, while a newly discovered encryption weakness in iOS 7 could put iPad and iPhone owners at risk.', 'VANCOUVER -- A change that Apple imposed to make iOS 7 more secure instead has dramatically weakened the security of devices running that mobile operating system, a security researcher has charged. At the CanSecWest conference here last week, Azimuth Security researcher Tarjei Mandt said that Apple made a major mistake when it changed its random-number generator to make its kernel encryption tougher in iOS 7. The kernel is the most basic level of an operating system and controls things like security, file management, and resource allocation. "In terms of security, it''s much worse than iOS 6," Mandt said.');

INSERT INTO comment (text, article_id, user_id) VALUES ('Losers, they can''t even get a PRNG right.', 3, 1);
INSERT INTO comment (text, article_id, user_id) VALUES ('PRNGs are actually quite hard to get right.', 3, 2);
INSERT INTO comment (text, article_id, user_id) VALUES ('I agree, but why don''t they just use input from all the sensors to (re-)seed the PRNG?', 3, 3);
INSERT INTO comment (text, article_id, user_id) VALUES ('Not good for your CV :)', 2, 4);