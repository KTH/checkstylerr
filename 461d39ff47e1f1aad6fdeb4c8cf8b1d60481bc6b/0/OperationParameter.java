package org.jboss.as.domain.http.server;

import java.util.Date;

/**
 * Value class for describing the result of an operation against the {@link DomainApiHandler}.
 * Used by {@link DomainUtil#writeResponse(io.undertow.server.HttpServerExchange, int, org.jboss.dmr.ModelNode, OperationParameter)}
 *
 * @author Harald Pehl
 * @date 05/14/2013
 */
public class OperationParameter {
    private final boolean get;
    private final int maxAge;
    private final Date lastModified;
    private final boolean encode;
    private final boolean pretty;

    private OperationParameter(Builder builder) {
        this.get = builder.get;
        this.maxAge = builder.maxAge;
        this.lastModified = builder.lastModified;
        this.encode = builder.encode;
        this.pretty = builder.pretty;
    }

    public boolean isGet() {
        return get;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public boolean isEncode() {
        return encode;
    }

    public boolean isPretty() {
        return pretty;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OperationResult{");
        sb.append("get=").append(get);
        sb.append(", maxAge=").append(maxAge);
        sb.append(", lastModified=").append(lastModified);
        sb.append(", encode=").append(encode);
        sb.append(", pretty=").append(pretty);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Builder for {@link OperationParameter}.
     */
    public static class Builder {
        // mandatory
        private final boolean get;
        // optional
        private int maxAge;
        private Date lastModified;
        private boolean pretty;
        private boolean encode;

        /**
         * Creates a new builder.
         * <p>Mandatory parameter</p>
         * <ol>
         *     <li>get</li>
         * </ol>
         * <p>Optional parameter (and their default values)</p>
         * <ul>
         *     <li>maxAge (0)</li>
         *     <li>lastModified (null)</li>
         *     <li>encode (false)</li>
         *     <li>pretty (false)</li>
         * </ul>
         *
         * @param get
         */
        public Builder(final boolean get) {
            this.get = get;
            this.maxAge = 0;
            this.lastModified = null;
            this.encode = false;
            this.pretty = false;
        }

        public Builder maxAge(int maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        public Builder lastModified(Date lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public Builder encode(boolean encode) {
            this.encode = encode;
            return this;
        }

        public Builder pretty(boolean pretty) {
            this.pretty = pretty;
            return this;
        }

        public OperationParameter build() {
            return new OperationParameter(this);
        }
    }
}
