##Data Definition Language

```sqlite
CREATE TABLE IF NOT EXISTS `Word`
(
    `word_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `word`    TEXT                              NOT NULL COLLATE NOCASE
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_Word_word` ON `Word` (`word`);

CREATE TABLE IF NOT EXISTS `Result`
(
    `result_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `word_id`        INTEGER,
    `text`           TEXT                              NOT NULL COLLATE NOCASE,
    `syllable_count` INTEGER                           NOT NULL,
    FOREIGN KEY (`word_id`) REFERENCES `Word` (`word_id`) ON UPDATE NO ACTION ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS `index_Result_word_id` ON `Result` (`word_id`);
```

[`ddl.sql`](sql/ddl.sql)