/*
* Copyright (c) 2015 by Casenet, LLC
*
* This file is protected by Federal Copyright Law, with all rights
* reserved. No part of this file may be reproduced, stored in a
* retrieval system, translated, transcribed, or transmitted, in any
* form, or by any means manual, electric, electronic, mechanical,
* electro-magnetic, chemical, optical, or otherwise, without prior
* explicit written permission from Casenet, LLC.
*/
package cz.dusanrychnovsky.xmlgenerator.schema.graph.generators;

import cz.dusanrychnovsky.xmlgenerator.schema.graph.ContentType;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.EmptyContent;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.EnumContent;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.StringContent;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.util.Arrays.asList;

class RandomValueGenerator {

    private List<Generator> generators = asList(
        new EmptyTypeGenerator(),
        new EnumTypeGenerator(),
        new StringTypeGenerator()
    );

    public String get(ContentType contentType) {

        for (Generator generator : generators) {
            if (generator.accepts(contentType)) {
                return generator.generate(contentType);
            }
        }

        throw new IllegalArgumentException(
            "Unsupported content type [" + contentType + "]."
        );
    }

    private abstract class Generator {

        public abstract boolean accepts(ContentType contentType);

        public abstract String generate(ContentType contentType);
    }

    private class EmptyTypeGenerator extends Generator {

        @Override
        public boolean accepts(ContentType contentType) {
            return (contentType instanceof EmptyContent);
        }

        @Override
        public String generate(ContentType contentType) {
            return "";
        }
    }

    private class EnumTypeGenerator extends Generator {

        private final Random rnd = new Random();

        @Override
        public boolean accepts(ContentType contentType) {
            return (contentType instanceof EnumContent);
        }

        @Override
        public String generate(ContentType contentType) {

            Set<String> values = ((EnumContent) contentType).getValues();

            int index = rnd.nextInt(values.size());
            for (String value : values) {

                if (index == 0) {
                    return value;
                }

                index--;
            }

            throw new AssertionError();
        }
    }

    private class StringTypeGenerator extends Generator {

        @Override
        public boolean accepts(ContentType contentType) {
            return (contentType instanceof StringContent);
        }

        @Override
        public String generate(ContentType contentType) {
            return RandomStringUtils.randomAlphabetic(10);
        }
    }
}
