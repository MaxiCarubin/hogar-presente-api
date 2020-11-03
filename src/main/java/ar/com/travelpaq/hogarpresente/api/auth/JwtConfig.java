package ar.com.travelpaq.hogarpresente.api.auth;

public class JwtConfig {
    public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";

    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEArK1U+JbkFKQLF5O3fkwF03+ZJQkQpQt4Cqr2d5JzOT28ZkTm\n" +
            "+RqaJoo3/rurC1CQ90MA7H9oqXmtrUOwhLFRE7ssyrZiFmRP/U+o9/iumzVl53IS\n" +
            "prncttaQMZP93yQGC3ZufeQItN1wC1TsLMhgxxZ4hH7rHRf3Fm/d8x3XjjGyMUEV\n" +
            "M5+8ep9UnIZOta5+4K5fJe72Df9C0CROVtCvuVJnLi3v8xqIsS5NYAaQiV5ofjOM\n" +
            "11ZthbfDH4b2tk9GO849OUUQCexMUF+FSLLDL8wFV9XRPybXUvu3fZx//bChO9UL\n" +
            "UEux+lTWo6izEYWq9RCxRZsNM7ZvfZF9KmnU/wIDAQABAoIBAAJsjHzQ4XLIur8h\n" +
            "+7lyJ8LIT8YNUoZ+mrjLnZogHC35h+OHpBZrIS7ZUd/pkoUeJGrULfcj8rwuURnV\n" +
            "kWzLcG/ueLp/xPC7h8PukliozQqwvTV6jHh6s3Z9nJiVk0LGpq8SgoqR4O1pDmGa\n" +
            "V5ZpWbJxxk9V2McXxwgxmdQH3zbHxeeHGpxt15sehPDzwd2hEUZ2mRxE8KRfpdCY\n" +
            "IntNX2OaSqZqewJAZsb/q2mAJ90lfveAeogp5vZvGRp5UQ0BaUHTRAn7E7f743Di\n" +
            "iTmn2+d+NpdzHXeYtE4bBArjqVYKP2mG4SbR0njew/P1V3Qvccvaa38XgwtU9zBN\n" +
            "jqUNWcECgYEA3RD2vAmv5v4aSgTflB7XHfM8Phqr/gkSjOBDjd86XgdS9vE3A/SS\n" +
            "hgt+FXVE2LbZ3QXs7YGMbCx0O/Wp6P19+89GFyrY2TS7OXTBvVXbKxIkFavxsUMF\n" +
            "YDHzYY+V2m3jCbgY1dqHMmVUwR1mkI6t1DYRYvL4l3NNV0OA3g810jUCgYEAx/bT\n" +
            "xUJMNPDMQmRBxVV39jS3QaMtkUDyx62qeEanYQGrVVQGG6bD1BD5AipSCLZQM0MB\n" +
            "DDwRe7Ce7SBJJq0AhaCzCeIlnptsOZlliq2IIlBES5dhpMuqZabJaFJIQOoTTzq/\n" +
            "6mnPRPRgsJe08Ry/6dz9QsWN1K6ZXf7dtt38sOMCgYEAr9eoi5YhB1qkSrBpr4aA\n" +
            "kmBCURwiJyXMTaat1ZH0+YpV4fupSUonqtxFZIgtRTzdO0RVW+6qZflOU8trZdsN\n" +
            "dVlAZXk9mDirnF5VJWe/uBjr/xkQXXH0OrpA4B+4kVDLQZXgm2sCE4Iq0pmyVdZI\n" +
            "Sv7fAZhHSSD8KieVwAzVQOkCgYAcTwc//9jGDej85TCifS0Uf/YJps5GqAk215Ew\n" +
            "oeAETbYxyEltFLjYhuaZteATN6w29Z9qWf6noiN9snporUlLoap3Fbr5r0stBFhp\n" +
            "v5NsOCzS/sVmDSc8aNZOnId6GSlOqCSPTRd4zZf6qB+HmuhYdV6tW0Zu+6Deu7wr\n" +
            "rVWevQKBgF49RsQh56Qw8IHQA9H2myhRs0GCe9cEulPQY+eL0eSCY7eSlAhXc6x2\n" +
            "rHPbExWxrxh+c7QKlQ+vT6MFtQAY2sOu3Q1sZq/zQNCluDBk19ltNAE+B2RSvBNo\n" +
            "lCLgZCDBP7tYX7jme69YhZ0h3AGK1prHSkKVAjmLB7JEEHPgcQ/Z\n" +
            "-----END RSA PRIVATE KEY-----\n";

    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArK1U+JbkFKQLF5O3fkwF\n" +
            "03+ZJQkQpQt4Cqr2d5JzOT28ZkTm+RqaJoo3/rurC1CQ90MA7H9oqXmtrUOwhLFR\n" +
            "E7ssyrZiFmRP/U+o9/iumzVl53ISprncttaQMZP93yQGC3ZufeQItN1wC1TsLMhg\n" +
            "xxZ4hH7rHRf3Fm/d8x3XjjGyMUEVM5+8ep9UnIZOta5+4K5fJe72Df9C0CROVtCv\n" +
            "uVJnLi3v8xqIsS5NYAaQiV5ofjOM11ZthbfDH4b2tk9GO849OUUQCexMUF+FSLLD\n" +
            "L8wFV9XRPybXUvu3fZx//bChO9ULUEux+lTWo6izEYWq9RCxRZsNM7ZvfZF9KmnU\n" +
            "/wIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";
}
