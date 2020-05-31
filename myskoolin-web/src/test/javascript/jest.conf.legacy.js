const tsconfig = require('../../../tsconfig.json');

module.exports = {
    preset: 'jest-preset-angular',
    setupFilesAfterEnv: ['<rootDir>/src/test/javascript/jest.ts'],
    cacheDirectory: '<rootDir>/target/jest-cache',
    coverageDirectory: '<rootDir>/target/test-results/',
    globals: {
        'ts-jest': {
            stringifyContentPathRegex: '\\.html$',
            tsConfig: '<rootDir>/tsconfig.json'
        }
    },
    /*"transform": {

    },*/
    transform: {
        '^.+\\.jsx?$': 'babel-jest',
        "^.+\\.(ts|tsx)$": "ts-jest"
    },
    moduleNameMapper: mapTypescriptAliasToJestAlias(),
    coveragePathIgnorePatterns: [
        '<rootDir>/src/test/javascript'
    ],
    reporters: [
        'default',
        ['jest-junit', {outputDirectory: './target/test-results/', outputName: 'TESTS-results-jest.xml'}]
    ],
    moduleDirectories: ['<rootDir>/node_modules'],
    "roots": [
        "<rootDir>/src/main/webapp/app"
    ],
    "modulePaths": [
        "<rootDir>/src/main/webapp/app",
    ],
    "testPathIgnorePatterns": [
        "<rootDir>/node_modules/"
    ],
    testResultsProcessor: 'jest-sonar-reporter',
    transformIgnorePatterns: ['<rootDir>/node_modules/'],
    //testMatch: ['<rootDir>/src/test/javascript/spec/app/shared/services/utils/*.+(ts|tsx|js)'],

    testMatch: [ "<rootDir>/src/test/javascript/spec/app/shared/services/utils/**/*.ts", "?(*.)+(spec|test).ts" ],
    rootDir: '../../../',
    testURL: 'http://localhost/',
};

function mapTypescriptAliasToJestAlias(alias = {}) {
    const jestAliases = {...alias};
    if (!tsconfig.compilerOptions.paths) {
        return jestAliases;
    }
    Object.entries(tsconfig.compilerOptions.paths)
        .filter(([, value]) => {
            // use Typescript alias in Jest only if this has value
            return !!value.length;

        })
        .map(([key, value]) => {
            // if Typescript alias ends with /* then in Jest:
            // - alias key must end with /(.*)
            // - alias value must end with /$1
            const regexToReplace = /(.*)\/\*$/;
            const aliasKey = key.replace(regexToReplace, '$1/(.*)');
            const aliasValue = value[0].replace(regexToReplace, '$1/$$1');
            if(aliasValue.includes('@')) {
                return [aliasKey, `<rootDir>/schoolme-app/${aliasValue}`];
            } else {
                return [aliasKey, `<rootDir>/${aliasValue}`];
            }

        })
        .reduce((aliases, [key, value]) => {
            aliases[key] = value;
            return aliases;
        }, jestAliases);
    return jestAliases;
}
