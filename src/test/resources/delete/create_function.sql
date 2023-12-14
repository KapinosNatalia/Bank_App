CREATE OR REPLACE FUNCTION mark_accounts_for_deletion(date_ character varying, status character varying)
    RETURNS setof accounts
-- AS
-- $$
UPDATE accounts SET status = $2
WHERE accounts.id in (
    SELECT
        accounts.id
    FROM accounts
    WHERE
            accounts.balance = 0
      AND accounts.created_at < TO_DATE($1, 'YYYY-MM-DD')
      AND accounts.status <> $2
      AND accounts.id NOT IN (SELECT transactions.credit_account_id FROM transactions)
      AND accounts.id NOT IN (SELECT transactions.debit_account_id FROM transactions)
);
SELECT * FROM accounts WHERE accounts.status = $2;
-- $$
LANGUAGE SQL;